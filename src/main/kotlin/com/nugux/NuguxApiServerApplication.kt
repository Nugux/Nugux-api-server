package com.nugux

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
open class NuguxApiServerApplication

fun main(args: Array<String>) {
    runApplication<NuguxApiServerApplication>(*args)
}
