package be.bnp.katas.tictactoe.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp

object TextualPointDefaults {
    val color = Color.Unspecified
    val backgroundColor = Color.White
}

@Composable
fun TextualPoint(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = TextualPointDefaults.color,
    backgroundColor: Color = TextualPointDefaults.backgroundColor,
    onClick: () -> Unit,
) {
    Card(
        modifier = modifier
            .aspectRatio(1f)
            .clickable(onClick = onClick),
        colors = CardDefaults.cardColors().copy(containerColor = backgroundColor)
    ) {
        Text(
            text,
            modifier = modifier
                .fillMaxSize()
                .wrapContentHeight(),
            color = color,
            fontSize = 24.sp,
            textAlign = TextAlign.Center,
            softWrap = true
        )
    }
}