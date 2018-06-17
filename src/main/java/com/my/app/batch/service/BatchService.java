package com.my.app.batch.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.my.app.batch.vo.BatchParameter;
import com.my.app.common.util.JsonUtil;

@Service
public class BatchService {

	public static void main(String[] args) {
		List<BatchParameter> batchParameters = new ArrayList<>();

		for (int i = 0; i < 10; i++) {
			BatchParameter batchParameter = new BatchParameter();
			batchParameter.setId(UUID.randomUUID().toString());
			batchParameter.setName("Batch-" + i);
			batchParameter.setDatetime(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
			batchParameters.add(batchParameter);
		}

		Map<String, String> map = batchParameters.stream().collect(Collectors.toMap(p -> p.getId(), p -> p.getName()));
		String json = JsonUtil.toJson(map);
		System.out.println(json);
	}

}
