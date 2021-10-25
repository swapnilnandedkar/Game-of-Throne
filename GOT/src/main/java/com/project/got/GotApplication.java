package com.project.got;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.got.dtos.BattleDTO;
import com.project.got.dtos.LocationDTO;
import com.project.got.dtos.RegionDTO;
import com.project.got.entities.*;
import com.project.got.repositories.*;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;


@SpringBootApplication
public class GotApplication
{

	public static void main(String[] args) throws Exception
	{
		SpringApplication.run(GotApplication.class, args);
	}
}
