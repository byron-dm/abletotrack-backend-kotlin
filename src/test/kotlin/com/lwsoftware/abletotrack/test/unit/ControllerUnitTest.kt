package com.lwsoftware.abletotrack.test.unit

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.lwsoftware.abletotrack.dto.request.LoginRequestDto
import com.lwsoftware.abletotrack.dto.response.LoginResponseDto
import com.lwsoftware.abletotrack.services.JwtTokenService
import com.lwsoftware.abletotrack.services.JwtUserDetailsService
import com.lwsoftware.abletotrack.services.UserService
import com.ninjasquad.springmockk.MockkBean
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

//@SpringBootTest
//@AutoConfigureMockMvc
//@ExtendWith(SpringExtension::class)
@WebMvcTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ControllerUnitTest() {

  @Autowired private lateinit var mockMvc: MockMvc

  @MockkBean private lateinit var authenticationManager: AuthenticationManager;
  @MockkBean private lateinit var jwtService: JwtUserDetailsService;
  @MockkBean private lateinit var jwtTokenService: JwtTokenService;
  @MockkBean private lateinit var userService: UserService;

  private val mapper = jacksonObjectMapper();
  private val bcryptEncoder = BCryptPasswordEncoder()

  @BeforeAll
  fun beforeAll() {
    //every { authenticationManager.authenticate(any()) } answers {TestingAuthenticationToken("", "")}
    /*every { jwtService.loadUserByUsername("asmith@gmail.com") } answers {
      User("asmith@gmail.com", "\$2y\$10\$k14F99evGD4nBc7f5kDTGe.p.DoMjhTbd2MyCx94N3O1aEY7frgVG", Collections.singletonList(
        SimpleGrantedAuthority(JwtUserDetailsService.ROLE_USER)
      ))
    }*/
  }

  @Test
  fun givenNoLoginInfo_ShouldFail() {
    mockMvc.perform(
      post("/login").accept(MediaType.APPLICATION_JSON)
        .content(mapper.writeValueAsString(LoginRequestDto("asmith@gmail.com", "asmith2023")))
    )
      .andDo(print())
  }

  @Test
  fun givenValidCredentials_ShouldSucceed() {
    mockMvc.perform(
      post("/user/login")
        .content(mapper.writeValueAsString(LoginRequestDto("asmith@gmail.com", "asmith2023", false)))
        .contentType(MediaType.APPLICATION_JSON)
    )
      .andDo(print())
      .andExpect(content().json(mapper.writeValueAsString(LoginResponseDto("", true, true, 1))))
      .andExpect(status().isOk())
  }

  @Test
  fun givenWrongCredentials_ShouldFail() {
    mockMvc.perform(
      post("/user/login")
        .content(mapper.writeValueAsString(LoginRequestDto("asmith@gmail.com", "asmith2022")))
        .contentType(MediaType.APPLICATION_JSON)
    )
      .andDo(print())
      .andExpect(content().json(mapper.writeValueAsString(LoginResponseDto())))
      .andExpect(status().isOk)
  }
}
