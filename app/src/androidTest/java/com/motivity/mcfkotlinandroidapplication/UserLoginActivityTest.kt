//package com.motivity.mcfkotlinandroidapplication
//
//import android.content.Intent
//import androidx.lifecycle.MutableLiveData
//import androidx.test.rule.ActivityTestRule
//import org.hamcrest.CoreMatchers.any
//import org.junit.Before
//import org.junit.Rule
//import org.junit.Test
//import org.junit.runner.RunWith
//import org.mockito.Mock
//import org.mockito.Mockito
//import org.mockito.Mockito.`when`
//import org.mockito.MockitoAnnotations
//import org.mockito.junit.MockitoJUnitRunner
//
//@RunWith(MockitoJUnitRunner::class)
//class UserLoginActivityTest {
//
//    @Mock
//    lateinit var viewModel: UserLoginViewModel
//
//    @Mock
//    lateinit var viewModelFactory: UserLoginViewModelFactory
//
//    @Mock
//    lateinit var userRepository: UserRepository
//
//    @get:Rule
//    var rule: ActivityTestRule<UserLoginActivity> = ActivityTestRule(UserLoginActivity::class.java, false, false)
//
//    @Before
//    fun setUp() {
//        MockitoAnnotations.initMocks(this)
//      //  `when`(viewModelFactory.create(Mockito.any())).thenReturn(viewModel)
//        `when`(viewModel.loginResult).thenReturn(MutableLiveData())
//        `when`(userRepository.loginUser(any(), any())).thenReturn(MockCall<ApiResponse<LoginResponse>>())
//    }
//
//    @Test
//    fun testSuccessfulLogin() {
//        val intent = Intent()
//        rule.launchActivity(intent)
//        viewModelFactory.create(UserLogin)
//        // Trigger login with valid credentials
//        onView(withId(R.id.email)).perform(typeText("user@example.com"))
//        onView(withId(R.id.password)).perform(typeText("password"))
//        onView(withId(R.id.login)).perform(click())
//
//        // Verify navigation to MainActivity
//        intended(hasComponent(MainActivity::class.java.name))
//        assertThat(rule.activity.isFinishing, `is`(true))
//    }
//
//    @Test
//    fun testEmptyEmailAndPassword() {
//        val intent = Intent()
//        rule.launchActivity(intent)
//
//        // Trigger login with empty email and password
//        onView(withId(R.id.login)).perform(click())
//
//        // Verify toast message
//        onView(withText("Email and password are required.")).inRoot(withDecorView(not(`is`(rule.activity.window.decorView)))).check(matches(isDisplayed()))
//    }
//
//    @Test
//    fun testEmptyEmail() {
//        val intent = Intent()
//        rule.launchActivity(intent)
//
//        // Trigger login with empty email
//        onView(withId(R.id.password)).perform(typeText("password"))
//        onView(withId(R.id.login)).perform(click())
//
//        // Verify toast message
//        onView(withText("Email and password are required.")).inRoot(withDecorView(not(`is`(rule.activity.window.decorView)))).check(matches(isDisplayed()))
//    }
//
//    @Test
//    fun testEmptyPassword() {
//        val intent = Intent()
//        rule.launchActivity(intent)
//
//        // Trigger login with empty password
//        onView(withId(R.id.email)).perform(typeText("user@example.com"))
//        onView(withId(R.id.login)).perform(click())
//
//        // Verify toast message
//        onView(withText("Email and password are required.")).inRoot(withDecorView(not(`is`(rule.activity.window.decorView)))).check(matches(isDisplayed()))
//    }
//
//    @Test
//    fun testNullLoginResponse() {
//        val intent = Intent()
//        rule.launchActivity(intent)
//
//        // Mock null login response
//        `when`(viewModel.loginResult.value).thenReturn(ApiResponse.Success(null))
//
//        // Trigger login with valid credentials
//        onView(withId(R.id.email)).perform(typeText("user@example.com"))
//        onView(withId(R.id.password)).perform(typeText("password"))
//        onView(withId(R.id.login)).perform(click())
//
//        // Verify toast message
//        onView(withText("Response body is null")).inRoot(withDecorView(not(`is`(rule.activity.window.decorView)))).check(matches(isDisplayed()))
//    }
//
//    @Test
//    fun testUnauthorizedAccess() {
//        val intent = Intent()
//        rule.launchActivity(intent)
//
//        // Mock unauthorized access (401)
//        `when`(viewModel.loginResult.value).thenReturn(ApiResponse.Error(401, "Unauthorized"))
//
//        // Trigger login with valid credentials
//        onView(withId(R.id.email)).perform(typeText("user@example.com"))
//        onView(withId(R.id.password)).perform(typeText("password"))
//        onView(withId(R.id.login)).perform(click())
//
//        // Verify toast message
//        onView(withText("Unauthorized")).inRoot(withDecorView(not(`is`(rule.activity.window.decorView)))).check(matches(isDisplayed()))
//    }
//
//    @Test
//    fun testOtherHttpError() {
//        val intent = Intent()
//        rule.launchActivity(intent)
//
//        // Mock other HTTP error
//        `when`(viewModel.loginResult.value).thenReturn(ApiResponse.Error(404, "Not Found"))
//
//        // Trigger login with valid credentials
//        onView(withId(R.id.email)).perform(typeText("user@example.com"))
//        onView(withId(R.id.password)).perform(typeText("password"))
//        onView(withId(R.id.login)).perform(click())
//
//        // Verify toast message
//        onView(withText("HTTP Error: 404: Not Found")).inRoot(withDecorView(not(`is`(rule.activity.window.decorView)))).check(matches(isDisplayed()))
//    }
//
//    @Test
//    fun testLoginButtonClickWithValidCredentials() {
//        val intent = Intent()
//        rule.launchActivity(intent)
//
//        // Trigger login with valid credentials
//        onView(withId(R.id.email)).perform(typeText("user@example.com"))
//        onView(withId(R.id.password)).perform(typeText("password"))
//        onView(withId(R.id.login)).perform(click())
//
//        // Verify navigation to MainActivity
//        intended(hasComponent(MainActivity::class.java.name))
//        assertThat(rule.activity.isFinishing, `is`(true))
//    }
//
//    @Test
//    fun testLoginButtonClickWithoutCredentials() {
//        val intent = Intent()
//        rule.launchActivity(intent)
//
//        // Trigger login without entering credentials
//        onView(withId(R.id.login)).perform(click())
//
//        // Verify toast message
//        onView(withText("Email and password are required.")).inRoot(withDecorView(not(`is`(rule.activity.window.decorView)))).check(matches(isDisplayed()))
//    }
//
//    @Test
//    fun testLifecycleMethods() {
//        val intent = Intent()
//        rule.launchActivity(intent)
//
//        // Verify the layout is set correctly
//        onView(withId(R.id.activity_user_login)).check(matches(isDisplayed()))
//
//        // Verify the view models are initialized
//        assertNotNull(rule.activity.viewModel)
//    }
//}
