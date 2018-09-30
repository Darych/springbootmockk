package springbootmockk

import org.springframework.stereotype.Service

@Service
class Service {
    fun getPersonalizedHello(name: String): String = "Hello, ${name.capitalize()}!"
}