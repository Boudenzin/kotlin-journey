package com.example.superheroes

import SuperheroesTheme
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.superheroes.model.Hero
import com.example.superheroes.model.HeroesRepository

@Composable
fun HeroListItem(hero: Hero, modifier: Modifier = Modifier) {

    var expanded by remember { mutableStateOf(false) }

    Card (
        elevation = CardDefaults.cardElevation(2.dp),
        modifier = modifier
            .animateContentSize()
    ) {
        Row (
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ){
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = stringResource(hero.nameRes),
                    style = MaterialTheme.typography.displaySmall
                )
                Text(
                    text = stringResource(hero.descriptionRes),
                    style = MaterialTheme.typography.bodyLarge
                )

                if (expanded) {
                    HeroDetails(hero = hero)
                }
                Spacer(modifier = Modifier.height(8.dp))
                IconButton(
                    onClick = {
                        expanded = !expanded
                    },
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                ) {
                    val icon = if (expanded) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore
                    Icon(
                        imageVector = icon,
                        contentDescription = stringResource(R.string.expand_button_content_description),
                        tint = MaterialTheme.colorScheme.secondary
                    )
                }

            }
            Spacer(modifier = Modifier.width(16.dp))
            Box(
                modifier = Modifier.size(72.dp)
            ) {
                Image(
                    painter = painterResource(id = hero.imageRes),
                    contentDescription = null,
                    contentScale = ContentScale.FillWidth,
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                )
            }
        }
    }
}

@Composable
fun HeroDetails(hero: Hero, modifier: Modifier = Modifier) {

    val abilitiesArray = LocalContext.current.resources.getStringArray(hero.abilitiesRes)

    Column(modifier = modifier
        .padding(
            start = 16.dp, top = 8.dp, bottom = 16.dp, end = 16.dp
        )
    ) {
        Text(text = "Origem: ${stringResource(id = hero.originRes)}")
        Text(text = "Primeira Aparição: ${stringResource(id = hero.firstAppearanceRes)}")
        Text(text = "Nível de Poder: ${hero.powerLevel}")

        Text(
            text = "Habilidades:",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(top = 4.dp, bottom = 2.dp)
        )

        abilitiesArray.forEach { ability ->
            Text(text = "• $ability") // Adiciona um marcador de tópico para estilo
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HeroesApp() {
    Scaffold (
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.app_name),
                        style = MaterialTheme.typography.displayLarge
                    )
                }
            )
        }
    ) { innerPadding ->
        LazyColumn (
            contentPadding = innerPadding
        ){
           items(HeroesRepository.heroes) { hero ->
               HeroListItem(
                   hero = hero,
                   modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
               )
           }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HeroesAppPreview() {
    SuperheroesTheme { // Use o nome do seu tema
        HeroesApp()
    }
}