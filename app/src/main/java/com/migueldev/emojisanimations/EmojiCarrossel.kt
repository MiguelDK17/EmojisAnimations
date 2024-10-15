package com.migueldev.emojisanimations

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun EmojiCarrossel(){
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current

    val excelente = stringResource(id = R.string.excelente)
    val bom = stringResource(id = R.string.bom)
    val ruim = stringResource(id = R.string.ruim)
    val horrivel = stringResource(id = R.string.horrivel)

    val avaliacao = listOf(excelente, bom, ruim, horrivel)
    val pagerState = rememberPagerState(initialPage = 0){
        avaliacao.size
    }


    val backgroundTint by animateColorAsState(
        when(pagerState.currentPage){
            0 -> Color(0xFFFFD700)// Dourado para 'Excelente'
            1 -> Color(0xFF4CAF50) // Verde para 'Bom'
            2 -> Color(0xF000A8FF) // Branco para 'Ruim'
            3 -> Color(0xFFF44336)// Vermelho para 'Insatisfeito'
            else -> Color(0xFFFFFFFF)
        }, label = "colorTransition"
    )

    val lottieFiles = listOf(
        R.raw.animacao_emoji_excelente,
        R.raw.animacao_emoji_bom,
        R.raw.animacao_emoji_neutro,
        R.raw.animacao_emoji_insatisfeito
    )
    /*viewModel.setOpiniao(avaliacao[pagerState.currentPage])
    Log.d(TAG, "AvaliacaoCarrossel: ${viewModel.opiniao.value}")*/
    var showBottomSheet by remember {
        mutableStateOf(false)
    }

    Box(modifier = Modifier.fillMaxWidth()){

        ColorTransitionFromCenter(targetColor = backgroundTint)
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
        ) {
            HorizontalPager(
                modifier = Modifier
                    .fillMaxWidth(),
                state = pagerState,

                ) { page ->
                EmojiLottieAnimation(
                    animationRes = lottieFiles[page], //Passa o arquivo Lottie correspondente
                    isPlaying = page == pagerState.currentPage, //toca a animação somente quando o item estiver em foco
                    modifier = Modifier.size(90.dp),
                    // Ajusta o tamanho do emoji,
                    avaliacao = avaliacao[page]
                )
            }

        }


    }

}