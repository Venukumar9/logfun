package com.motivity.mcfkotlinandroidapplication

import androidx.test.core.app.ApplicationProvider

import android.content.Context
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Before
import org.junit.BeforeClass
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(AndroidJUnit4::class)
class UserLoginActivityTest {

    @Mock
    private lateinit var mockViewModel: UserLoginViewModel

    private lateinit var activity: UserLoginActivity


    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        val context = InstrumentationRegistry.getInstrumentation().targetContext

        //val context = ApplicationProvider.getApplicationContext<Context>()
        activity = UserLoginActivity()
        activity.viewModel = mockViewModel
        // Use InstrumentationRegistry to get context
      //  doNothing().`when`(activity).setContentView(anyInt())
       // `when`(activity.viewModel).thenReturn(mockViewModel)
       // val context = ApplicationProvider.getApplicationContext<Context>()

        // Set up your view elements here if needed
    }

    @Test
    fun testLoginButtonClick() {
        val mockButton = mock(Button::class.java)
        val mockEmailTextView = mock(TextView::class.java)
        val mockPasswordTextView = mock(TextView::class.java)

        `when`(activity.findViewById<Button>(R.id.login)).thenReturn(mockButton)
        `when`(activity.findViewById<TextView>(R.id.email)).thenReturn(mockEmailTextView)
        `when`(activity.findViewById<TextView>(R.id.password)).thenReturn(mockPasswordTextView)

        // Set up the expected behavior when the login button is clicked
        `when`(mockButton.setOnClickListener(any())).thenAnswer {
            val clickListener = it.getArgument(0) as View.OnClickListener
            clickListener.onClick(mockButton)
        }

        // Set up some values for email and password
        `when`(mockEmailTextView.text).thenReturn("test@example.com")
        `when`(mockPasswordTextView.text).thenReturn("password")

        // Trigger the button click
        mockButton.performClick()

        // Verify that the login method in the view model is called
        verify(mockViewModel).loginUser("test@example.com", "password")
    }
}