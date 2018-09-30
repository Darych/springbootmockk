package springbootmockk

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class Controller(private val service: Service) {
    @GetMapping("/hello")
    fun hello(@RequestParam name: String) = service.getPersonalizedHello(name)
}