package springbootmockk

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@ExtendWith(SpringExtension::class)
@WebMvcTest(Controller::class)
internal class ControllerTest{

    @TestConfiguration
    class ControllerTestConfig {
        @Bean
        fun service() = mockk<Service>()
    }

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var service: Service

    @Test
    fun `Spring context loaded`() {
    }

    @Test
    fun `Hello returns correct message`() {
        val lowerCaseName = "username"
        val expectedMessage = "Hello, Username!"
        every { service.getPersonalizedHello(lowerCaseName) } returns expectedMessage

        val result = mockMvc.perform(get("/hello?name=$lowerCaseName")).andExpect(status().isOk).andDo(print()).andReturn()

        assertEquals(expectedMessage, result.response.contentAsString)
        verify { service.getPersonalizedHello(lowerCaseName) }
    }
}