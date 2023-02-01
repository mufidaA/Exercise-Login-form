package com.example.login

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.login.ui.theme.LogInTheme
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.constraintlayout.compose.Visibility

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LogInTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Login()
                }
            }
        }
    }
}

@Composable
fun Login() {
    var User: String by remember { mutableStateOf("") }
    var Password by rememberSaveable { mutableStateOf("") }
    var PasswordVisible by rememberSaveable { mutableStateOf(false) }
    Column {
            Text (
                text = stringResource(R.string.title),
                color = MaterialTheme.colors.primary,
                fontSize = 24.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.run { fillMaxWidth().padding(top = 16.dp,bottom= 16.dp) }
                    )
        OutlinedTextField  (
            value = User,
            onValueChange = {User = it},
            leadingIcon = { Icon(imageVector = Icons.Default.Email, contentDescription = "Email Icon") },
            trailingIcon = { Icon(imageVector = Icons.Default.Person, contentDescription = "Person Icon") },
            label = { Text ("User Name" )},
            placeholder = { Text ("Type Username here" )},
            singleLine= true,
            keyboardOptions =  KeyboardOptions (keyboardType = KeyboardType.Text)

            )
            OutlinedTextField  (
                value = Password,
                onValueChange = {Password = it},
                label = { Text ("Password" )},
                placeholder = { Text("Type password here") },
                singleLine= true,
                visualTransformation = if (PasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                trailingIcon = {
                    val image = if (PasswordVisible)
                        Icons.Filled.Visibility
                    else Icons.Filled.VisibilityOff

                    // Please provide localized description for accessibility services
                    val description = if (PasswordVisible) "Hide password" else "Show password"

                    IconButton(onClick = {PasswordVisible = !PasswordVisible}){
                        Icon(imageVector = Icons.Default.Lock, contentDescription = "password Icon") }
                }

            )
            Row() {
              Button(onClick = { /*TODO*/ }) {
                  Text(
                      modifier = Modifier.padding(3.dp),
                      text = "LogIn",
                      style = TextStyle(fontSize = 15.sp)
                  )
                }
            }
        Text (
            text = "Forgot your password?",
            modifier = Modifier.clickable(onClick = {
                val website = Uri.parse("https://moodle.oulu.fi/pluginfile.php/1504881/mod_resource/content/2/LoginForm.pdf")
                val intent = Intent(Intent.ACTION_VIEW, website)
            }
            )  )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    LogInTheme {
        Login()
    }
}