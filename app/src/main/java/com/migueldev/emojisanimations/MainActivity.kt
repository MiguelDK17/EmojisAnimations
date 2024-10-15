package com.migueldev.emojisanimations

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.migueldev.emojisanimations.ui.theme.EmojisAnimationsTheme
import com.airbnb.lottie.compose.rememberLottieComposition

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EmojisAnimationsTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    EmojiCarrossel()
                }
            }
        }
    }
}


@Composable
fun EmojiLottieAnimation(
    animationRes: Int,
    isPlaying: Boolean,
    modifier: Modifier = Modifier,
    avaliacao: String){
    //Carrega a animação do arquivo JSON
    val composition by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(animationRes))

    //Estado da animação
    val progress by animateLottieCompositionAsState(
        composition = composition,
        isPlaying = isPlaying,
        iterations = LottieConstants.IterateForever //Repete indefinidamente
    )

    //Renderiza a animação no Composable
    Column(horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()) {


        LottieAnimation(
            composition = composition,
            progress = { progress },
            modifier = modifier
        )
        Spacer(modifier = Modifier
            .size(40.dp)
        )
        Text(text = avaliacao,
            style = MaterialTheme.typography.bodyLarge
        )


    }
}
