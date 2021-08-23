package com.example.rickandmortycompose.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import coil.compose.rememberImagePainter
import com.example.rickandmortycompose.R
import com.example.rickandmortycompose.mvvm.RiMViewModel
import com.example.rickandmortycompose.retrofit.characters.Character
import com.example.rickandmortycompose.ui.theme.RickAndMortyComposeTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainFragment @Inject constructor() : Fragment() {


    @Inject
    lateinit var characterFragment: CharacterFragment

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {

            setContent {
                RickAndMortyComposeTheme {

                    Surface(
                        color = MaterialTheme.colors.primary,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        MyApp()

                    }
                }
            }
        }
    }

    @Composable
    fun MyApp(viewModel: RiMViewModel = viewModel()) {
        var listOfCharacters : MutableList<Character?> = mutableListOf()
        var list :List<Character>? = listOf()




        for (x in 1..32){
             list = viewModel.listOfCharacters.value[x]
            list?.forEach { listOfCharacters.add(it)  }

        }
          val listTEST = viewModel.listOfCharactersTEST.value//I don't know If i delete this line App no display characters WTF !!!!


        if (list != null) {
            ListOfCharacters(list = listOfCharacters)
        }


    }

    @Composable
    fun ListOfCharacters(list: MutableList<Character?>) {

        LazyColumn(
            Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(list) {
                ItemList(it)
            }
        }

    }

    @Composable
    fun ItemList(character: Character?) {
        val navController = rememberNavController()
        NavHost(navController = navController , startDestination = "main" ){
            composable("main"){MainFragment()  }
            composable("character"){Test()
            }
        }
        Card(
            backgroundColor = MaterialTheme.colors.error,
            shape = MaterialTheme.shapes.small,
            modifier = Modifier
                .padding(top = 6.dp, bottom = 6.dp, start = 15.dp, end = 15.dp)
                .clickable {
                    Toast
                        .makeText(context, "${character?.name}", Toast.LENGTH_SHORT)
                        .show()


                },

            elevation = 8.dp
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Row() {
                    Button(onClick = { navController.navigate("character") }) {

                    }
                    Image(
                        painter = rememberImagePainter(character?.image),
                        contentDescription = null,
                        modifier = Modifier.size(250.dp),

                        )
                }
                Row(
                    Modifier.width(250.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(character?.name.toString(), maxLines = 1,color = MaterialTheme.colors.primaryVariant,style = MaterialTheme.typography.body2)
                }
            }

        }
    }

    @Composable
    fun Test() {
        Text(text = "test")
    }

    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview(viewModel: RiMViewModel = viewModel()) {

        //ListOfCharacters(list = viewModel.listOfCharacters.value)
    }
}