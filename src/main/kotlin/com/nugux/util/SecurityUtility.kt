package com.nugux.util

import java.io.FileInputStream
import java.io.FileOutputStream
import java.util.*

fun getProjectKey(): String {
    val properties = Properties()
    val propertiesFile = System.getProperty("user.dir") + "\\key.properties";
    val inputStream = FileInputStream(propertiesFile)
    properties.load(inputStream)

    return properties["ProjectKey"] as String
}
