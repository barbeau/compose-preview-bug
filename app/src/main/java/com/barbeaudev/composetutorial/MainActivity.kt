package com.barbeaudev.composetutorial

import android.location.Location
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.barbeaudev.composetutorial.Utils.isSpeedAndBearingAccuracySupported
import com.barbeaudev.composetutorial.ui.theme.ComposeTutorialTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeTutorialTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    LocationCard(newLocation())
                }
            }
        }
    }
}

@Preview
@Composable
fun LocationCardPreview(
    @PreviewParameter(LocationPreviewParameterProvider::class) location: Location
) {
    LocationCard(location)
}

class LocationPreviewParameterProvider : PreviewParameterProvider<Location> {
    override val values = sequenceOf(newLocation())
}

fun newLocation(): Location {
    val l = Location("temp")
    l.apply {
        latitude = 28.92973474
        longitude = -87.4345494
        time = 1633375741711
        altitude = 13.5
        speed = 21.5f
        bearing = 240f
        accuracy = 123f
        if (isSpeedAndBearingAccuracySupported()) {
            speedAccuracyMetersPerSecond = 1f
            bearingAccuracyDegrees = 2f
        }
    }
    return l
}

@Composable
fun LocationCard(
    location: Location
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp),
        elevation = 2.dp
    ) {
        Row {
            LabelColumn1()
            ValueColumn1(location)
            LabelColumn2()
            ValueColumn2(location)
        }
    }
}

@Composable
fun ValueColumn1(location: Location) {
    Column(
        modifier = Modifier
            .wrapContentHeight()
            .wrapContentWidth()
            .padding(top = 5.dp, bottom = 5.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start
    ) {
        LocationValue(location.latitude.toString())
        LocationValue(location.longitude.toString())
        LocationValue(location.altitude.toString())
        LocationValue(location.speed.toString())
        if (isSpeedAndBearingAccuracySupported()) {
            LocationValue(if (location.hasSpeedAccuracy()) location.speedAccuracyMetersPerSecond.toString() else "")
        }
    }
}

@Composable
fun ValueColumn2(location: Location) {
    Column(
        modifier = Modifier
            .wrapContentHeight()
            .wrapContentWidth()
            .padding(top = 5.dp, bottom = 5.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start
    ) {
        LocationValue(location.time.toString())
        LocationValue("3 sec")
        Accuracy(location)
        LocationValue(location.bearing.toString())
        if (isSpeedAndBearingAccuracySupported()) {
            LocationValue(if (location.hasBearingAccuracy()) location.bearingAccuracyDegrees.toString() else "")
        }
    }
}

/**
 * Horizontal and vertical location accuracies based on the provided location
 * @param location
 */
@Composable
fun Accuracy(location: Location) {
    if (location.hasAccuracy()) {
        LocationValue(location.accuracy.toString())
    } else {
        LocationValue("")
    }
}

@Composable
fun LabelColumn1() {
    Column(
        modifier = Modifier
            .wrapContentHeight()
            .wrapContentWidth()
            .padding(top = 5.dp, bottom = 5.dp, start = 5.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.End
    ) {
        LocationLabel("Lat:")
        LocationLabel("Long:")
        LocationLabel("Alt:")
        LocationLabel("Speed:")
        LocationLabel("Speed Acc:")
    }
}

@Composable
fun LabelColumn2() {
    Column(
        modifier = Modifier
            .wrapContentHeight()
            .wrapContentWidth()
            .padding(5.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.End
    ) {
        LocationLabel("Time:")
        LocationLabel("TTFF:")
        LocationLabel("H/V Acc:")
        LocationLabel("Bearing:")
        LocationLabel("Bearing Acc:")
    }
}

@Composable
fun LocationLabel(label: String) {
    Text(
        text = label,
        modifier = Modifier.padding(start = 4.dp, end = 4.dp),
        fontWeight = FontWeight.Bold,
        fontSize = 13.sp,
    )
}

@Composable
fun LocationValue(text: String) {
    Text(
        text = text,
        modifier = Modifier.padding(end = 4.dp),
        fontSize = 13.sp,
    )
}