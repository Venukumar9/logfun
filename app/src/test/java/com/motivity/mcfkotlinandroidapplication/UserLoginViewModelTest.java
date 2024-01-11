//package com.motivity.mcfkotlinandroidapplication;
//
//
//import org.junit.Rule;
//import org.junit.Test;
//import org.mockito.junit.MockitoJUnit;
//import org.mockito.junit.MockitoRule;
//
//class UserLoginViewModelTest {
//
//    @get:Rule
//    val rule: MockitoRule = MockitoJUnit.rule()
//
//    @get:Rule
//    val taskExecutorRule = InstantTaskExecutorRule()
//
//    @Mock
//    private lateinit var userRepository: UserRepository
//
//    @Mock
//    private lateinit var loginResultObserver: Observer<ApiResponse<LoginResponse>>
//
//    private lateinit var viewModel: UserLoginViewModel
//
//    @Before
//    fun setup() {
//        viewModel = UserLoginViewModel(userRepository)
//        viewModel.loginResult.observeForever(loginResultObserver)
//    }
//
//    @Test
//    fun loginUserSuccess() {
//        // Mock successful login response
//        val successResponse = LoginResponse(accessToken = "dummyToken")
//        `when`(userRepository.userLogin(anyString(), anyString(), any())).thenAnswer {
//            (it.arguments[2] as (ApiResponse<LoginResponse>) -> Unit).invoke(ApiResponse.Success(successResponse))
//        }
//
//        // Trigger login
//        viewModel.loginUser("test@email.com", "password")
//
//        // Verify that the observer is notified with the success response
//        verify(loginResultObserver).onChanged(ApiResponse.Success(successResponse))
//    }
//
//    @Test
//    fun loginUserError() {
//        // Mock error response
//        val errorResponse = ApiResponse.Error(code = 401, message = "Unauthorized")
//        `when`(userRepository.userLogin(anyString(), anyString(), any())).thenAnswer {
//            (it.arguments[2] as (ApiResponse<LoginResponse>) -> Unit).invoke(errorResponse)
//        }
//
//        // Trigger login
//        viewModel.loginUser("test@email.com", "password")
//
//        // Verify that the observer is notified with the error response
//        verify(loginResultObserver).onChanged(errorResponse)
//    }
//}
