package me.brisson.ecomlabs.util

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import me.brisson.ecomlabs.R
import me.brisson.ecomlabs.data.model.Product
import me.brisson.ecomlabs.data.model.mockedProduct
import me.brisson.ecomlabs.ui.theme.EcomLabsTheme
import me.brisson.ecomlabs.ui.theme.openSans


@ExperimentalMaterial3Api
@Composable
fun HorizontalProduct(
    modifier: Modifier = Modifier,
    product: Product,
    onClick: () -> Unit
) {
    Card(
        modifier = modifier.height(110.dp),
        shape = RoundedCornerShape(18.dp),
        elevation = CardDefaults.elevatedCardElevation(),
        colors = CardDefaults.elevatedCardColors(),
        onClick = onClick
    ) {
        Row(
            modifier = Modifier.background(color = MaterialTheme.colorScheme.surface),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            SubcomposeAsyncImage(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(2f),
                model = ImageRequest.Builder(LocalContext.current)
                    .data(product.image)
                    .crossfade(true)
                    .build(),
                contentDescription = stringResource(id = R.string.profile_image_content_description),
                loading = { CircularProgressIndicator() },
                contentScale = ContentScale.Crop,
                alignment = Alignment.Center
            )
            Column(
                modifier = Modifier
                    .weight(3f)
                    .fillMaxHeight()
                    .padding(vertical = 12.dp, horizontal = 12.dp),
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                Text(
                    modifier = Modifier,
                    text = product.name,
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.onSurface,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                )
                Row(verticalAlignment = Alignment.Bottom) {
                    Text(
                        text = "$ ${product.currentPrice}",
                        style = MaterialTheme.typography.headlineSmall,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    product.oldPrices?.let {
                        Text(
                            modifier = Modifier.padding(start = 6.dp),
                            text = "$ ${product.oldPrices.first()}",
                            style = TextStyle(
                                fontFamily = openSans,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Normal,
                                lineHeight = 20.sp,
                                color = MaterialTheme.colorScheme.outline,
                                textDecoration = TextDecoration.LineThrough,
                            ),
                        )
                    }
                }
            }
        }
    }
}

@ExperimentalMaterial3Api
@Composable
fun VerticalProduct(
    modifier: Modifier = Modifier,
    product: Product,
    onClick: () -> Unit
) {
    Card(
        modifier = modifier.height(230.dp),
        shape = RoundedCornerShape(18.dp),
        elevation = CardDefaults.elevatedCardElevation(),
        colors = CardDefaults.elevatedCardColors(),
        onClick = onClick
    ) {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .background(color = MaterialTheme.colorScheme.surface),
        ) {
            SubcomposeAsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(125.dp),
                model = ImageRequest.Builder(LocalContext.current)
                    .data(product.image)
                    .crossfade(true)
                    .build(),
                contentDescription = stringResource(id = R.string.profile_image_content_description),
                loading = { CircularProgressIndicator() },
                contentScale = ContentScale.Crop,
                alignment = Alignment.Center
            )
            Column(
                modifier = Modifier
                    .padding(12.dp)
                    .fillMaxWidth()
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.SpaceAround
            ) {
                Text(
                    modifier = Modifier,
                    text = product.name,
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.onSurface,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.Bottom,
                    horizontalArrangement = Arrangement.Start
                ) {
                    Text(
                        text = "$ ${product.currentPrice}",
                        style = MaterialTheme.typography.headlineSmall,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    product.oldPrices?.let {
                        Text(
                            modifier = Modifier.padding(start = 6.dp),
                            text = "$ ${product.oldPrices.first()}",
                            style = TextStyle(
                                fontFamily = openSans,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Normal,
                                lineHeight = 20.sp,
                                color = MaterialTheme.colorScheme.outline,
                                textDecoration = TextDecoration.LineThrough,
                            ),
                        )
                    }
                }
            }
        }

    }
}

@ExperimentalMaterial3Api
@Preview
@Composable
fun PreviewHorizontalProduct() {
    EcomLabsTheme {
        HorizontalProduct(product = mockedProduct, onClick = { })
    }
}


@ExperimentalMaterial3Api
@Preview
@Composable
fun PreviewVerticalProduct() {
    EcomLabsTheme {
        VerticalProduct(product = mockedProduct, onClick = { })
    }
}