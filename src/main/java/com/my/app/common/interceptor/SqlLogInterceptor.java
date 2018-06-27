package com.my.app.common.interceptor;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.LoggerFactory;
import org.springframework.util.ClassUtils;

@Intercepts({
		@Signature(type = Executor.class, method = "query", args = { MappedStatement.class, Object.class,
				RowBounds.class, ResultHandler.class }),
		@Signature(type = Executor.class, method = "update", args = { MappedStatement.class, Object.class }) })
public class SqlLogInterceptor implements Interceptor {

	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		Object[] args = invocation.getArgs();
		MappedStatement ms = (MappedStatement) args[0];
		BoundSql boundSql = ms.getBoundSql(args[1]);
		// String sql = boundSql.getSql().replaceAll("\\s+", " "); // 공백 제거
		StringBuilder sql = new StringBuilder(boundSql.getSql());

		// 파라미터 처리
		Object parameterObject = boundSql.getParameterObject();
		if (parameterObject != null) {
			List<Object> parameters = new ArrayList<>();

			if (ClassUtils.isPrimitiveOrWrapper(parameterObject.getClass())) {
				parameters.add(parameterObject);
			} else if (parameterObject instanceof String) {
				parameters.add(parameterObject);
			} else {
				for (ParameterMapping parameterMapping : boundSql.getParameterMappings()) {
					String property = parameterMapping.getProperty();
					Object value = null;

					if (boundSql.hasAdditionalParameter(property)) {
						value = boundSql.getAdditionalParameter(property);
					} else {
						value = PropertyUtils.getProperty(parameterObject, property);
					}

					parameters.add(value);
				}
			}

			Map<Integer, Integer> map = new LinkedHashMap<>();
			Pattern pattern = Pattern.compile("\\?");
			Matcher matcher = pattern.matcher(sql);
			while (matcher.find()) {
				map.put(matcher.start(), matcher.end());
			}

			List<Integer> keys = new ArrayList<>(map.keySet());
			for (int i = keys.size() - 1; i >= 0; i--) {
				int start = keys.get(i).intValue();
				int end = map.get(start).intValue();

				Object parameter = parameters.get(i);
				String value = null;

				if (parameter == null) {
					value = "NULL";
				} else if (parameter instanceof String) {
					value = "'" + parameter.toString() + "'";
				} else {
					value = parameter.toString();
				}

				sql.replace(start, end, value);
			}
		}

		// SQL 실행
		long start = System.nanoTime();
		Object proceed = null;

		try {
			proceed = invocation.proceed();
		} catch (Exception e) {
			LoggerFactory.getLogger(ms.getId()).info(sql.toString());
			throw e;
		}

		long end = System.nanoTime();
		Object count = null;
		if (proceed instanceof List) {
			count = ((List<?>) proceed).size();
		} else {
			count = proceed;
		}

		// SQL 로깅
		StringBuilder executed = new StringBuilder();
		executed.append("/* ").append(ms.getId()).append(" */");
		executed.append(System.lineSeparator());
		executed.append("==> Query: ").append(sql);
		executed.append(System.lineSeparator());
		executed.append("<== Count: ").append(count).append(" (").append("executed ")
				.append(TimeUnit.NANOSECONDS.toMillis(end - start)).append(" ms)");
		LoggerFactory.getLogger(ms.getId()).info(executed.toString());

		return proceed;
	}

	@Override
	public Object plugin(Object target) {
		return Plugin.wrap(target, this);
	}

	@Override
	public void setProperties(Properties properties) {

	}

}
