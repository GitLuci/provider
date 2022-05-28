package com.providerwebservice.intro_provider

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux
import java.time.Duration
import java.time.LocalDateTime
import java.time.LocalDateTime.*
import java.util.concurrent.ThreadLocalRandom

@SpringBootApplication
class Application

fun main(args: Array<String>) {
	runApplication<Application>(*args)
}

@RestController
class RestController{

	private fun randomStockPrice():Double{
		return ThreadLocalRandom.current().nextDouble(100.0)
	}
												//tipo de info q vou passaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa
	@GetMapping(value = ["/stocks/{symbol}"], produces = [MediaType.TEXT_EVENT_STREAM_VALUE])
	fun prices(@PathVariable symbol: String) : Flux<StockPrice> {
		return Flux.interval(Duration.ofSeconds(3)).map { StockPrice(symbol,randomStockPrice(), now()) }
	}

	@GetMapping("/bomdia")
	protected fun sayHello(): String {
		return "Ola turma IDM A!"
	}

	@PostMapping("/boanoite")
	fun sayNoite(): String {
		return "Ola boa noite IDM A!"
	}						//pertence as regras do protocolo / regras do formato ou linguagem
	@PostMapping(value = ["/mymsg", "/msg"], consumes = ["aplication/json"])
	fun myMsg(@RequestBody msg:String): String {

		return msg + "XPTO body"
	}
}

data class StockPrice(val symbol:String, val price:Double, val time:LocalDateTime)