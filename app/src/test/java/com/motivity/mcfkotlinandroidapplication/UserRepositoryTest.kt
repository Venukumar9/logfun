package com.motivity.mcfkotlinandroidapplication

import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertTrue
import okhttp3.Request
import okio.Timeout
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@RunWith(MockitoJUnitRunner::class)
class UserRepositoryTest {

    @Mock
    lateinit var api: Api

    lateinit var userRepository: UserRepository

    @Before
    fun setUp() {
        userRepository = UserRepository(api)
    }

    @Test
    fun `userLogin`() {
        // Arrange
        val email = "user@example.com"
        val password = "password"
        val callback: (ApiResponse<LoginResponse>) -> Unit = mock()
        val call: Call<LoginResponse> = mock()
        val expectedLoginResponse = LoginResponse("accessToken")

        `when`(api.userLogin(UserLogin(email, password), "application/json"))
            .thenReturn(object : Call<LoginResponse> {
                override fun enqueue(callback: Callback<LoginResponse>) {
                    callback.onResponse(this, Response.success(expectedLoginResponse))
                }
                override fun execute(): Response<LoginResponse> {
                    return Response.success(expectedLoginResponse)
                }

                override fun isExecuted(): Boolean = false
                override fun cancel() {
                    TODO("Not yet implemented")
                }

                override fun isCanceled(): Boolean {
                    TODO("Not yet implemented")
                }

                override fun request(): Request {
                    TODO("Not yet implemented")
                }

                override fun timeout(): Timeout {
                    TODO("Not yet implemented")
                }

                override fun clone(): Call<LoginResponse> = this

                // Implement other methods as needed for your test
            })

        // Perform the login and check the result
        userRepository.userLogin(email, password) { result ->
            // Check if the result is of type ApiResponse.Success
            assertTrue(result is ApiResponse.Success)

            // Check if the result contains the expected login response data
            assertEquals(expectedLoginResponse, (result as ApiResponse.Success).data)
        }

    }
//
//    @Test
//    fun `userLogin failure`() {
//        // Arrange
//        val email = "user@example.com"
//        val password = "password"
//        val callback: (ApiResponse<LoginResponse>) -> Unit = mock()
//        val call: Call<LoginResponse> = mock()
//
//        whenever(api.userLogin(any(), any())).thenReturn(call)
//        whenever(call.enqueue(any())).doAnswer {
//            val callback: Callback<LoginResponse> = it.getArgument(0)
//            callback.onFailure(call, Throwable("Network error"))
//        }
//
//        // Act
//        userRepository.userLogin(email, password, callback)
//
//        // Assert
//        verify(callback).invoke(argThat { this is ApiResponse.Error && this.message == "Network error" })
//    }
}
