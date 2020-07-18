package com.nugux.util

import com.github.doyaaaaaken.kotlincsv.dsl.csvReader
import org.springframework.core.io.ClassPathResource

fun readCsv(filePath: String) = csvReader().readAll(ClassPathResource(filePath).file)
