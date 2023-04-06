package com.lwsoftware.abletotrack.integration

import com.lwsoftware.abletotrack.services.UserService
import org.hamcrest.CoreMatchers.`is`
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status


@SpringBootTest
@AutoConfigureMockMvc
class ControllerIntegrationTest {

  @Autowired
  private lateinit var mvc: MockMvc

  @Autowired
  private lateinit var userService: UserService;

  @Test
  fun loginTest() {
    mvc.perform(
      get("/api/employees")
        .contentType(MediaType.APPLICATION_JSON)
    )
      .andExpect(status().isOk())
      .andExpect(
        content()
          .contentTypeCompatibleWith(MediaType.APPLICATION_JSON)
      )
      .andExpect(jsonPath("$[0].name", `is`("bob")))
  }

}
