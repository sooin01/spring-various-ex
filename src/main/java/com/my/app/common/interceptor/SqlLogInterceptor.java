package com.my.app.common.interceptor;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

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
		String sql = boundSql.getSql().replaceAll("\\s+", " ");
		StringBuilder sb = new StringBuilder();

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
					Object value = PropertyUtils.getProperty(parameterObject, parameterMapping.getProperty());

					if (value == null) {
						value = boundSql.getAdditionalParameter(parameterMapping.getProperty());
					}

					parameters.add(value);
				}
			}

			if (parameters.size() > 0) {
				String[] sqlSplit = sql.split("\\?");

				for (int i = 0; i < parameters.size(); i++) {
					Object parameter = parameters.get(i);
					String value = null;

					if (parameter == null) {
						value = "NULL";
					} else if (parameter instanceof String) {
						value = "'" + parameter.toString() + "'";
					} else {
						value = parameter.toString();
					}

					sb.append(sqlSplit[i]).append(value);
				}

				sb.append(sqlSplit[sqlSplit.length - 1]);
			} else {
				sb.append(sql);
			}
		} else {
			sb.append(sql);
		}

		// SQL 실행
		long start = System.nanoTime();
		Object proceed = invocation.proceed();
		long end = System.nanoTime();

		// SQL 로깅
		StringBuilder executed = new StringBuilder();
		executed.append("sql count: ");
		if (proceed instanceof List) {
			executed.append(((List<?>) proceed).size());
		} else {
			executed.append(proceed);
		}
		executed.append(", ");
		executed.append("executed ").append(TimeUnit.NANOSECONDS.toMillis(end - start)).append(" ms")
				.append(System.lineSeparator()).append("/* ").append(ms.getId()).append(" */")
				.append(System.lineSeparator());
		String bindingSql = sb.insert(0, executed).toString();
		LoggerFactory.getLogger(ms.getId()).info(bindingSql);

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
