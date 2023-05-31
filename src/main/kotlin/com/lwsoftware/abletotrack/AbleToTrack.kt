package com.lwsoftware.abletotrack

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication(/*exclude = [SecurityAutoConfiguration::class]*/)
class AbleToTrack

fun main(args: Array<String>) {
  runApplication<AbleToTrack>(*args)
}
