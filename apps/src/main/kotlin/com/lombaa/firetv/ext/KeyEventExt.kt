package com.lombaa.firetv.ext

import android.view.KeyEvent

fun KeyEvent.isSelect(): Boolean {
    return keyCode == KeyEvent.KEYCODE_ENTER
            || keyCode == KeyEvent.KEYCODE_NUMPAD_ENTER
            || keyCode == KeyEvent.KEYCODE_DPAD_CENTER
            || keyCode == KeyEvent.KEYCODE_BUTTON_SELECT
            || keyCode == KeyEvent.KEYCODE_A
}

fun KeyEvent.isRight(): Boolean {
    return keyCode == KeyEvent.KEYCODE_DPAD_RIGHT
}

fun KeyEvent.isLeft(): Boolean {
    return keyCode == KeyEvent.KEYCODE_DPAD_LEFT
}

fun KeyEvent.isUp(): Boolean {
    return keyCode == KeyEvent.KEYCODE_DPAD_UP
}

fun KeyEvent.isDown(): Boolean {
    return keyCode == KeyEvent.KEYCODE_DPAD_DOWN
}

fun KeyEvent.isNavigation(): Boolean {
    return isRight() || isLeft() || isUp() || isDown()
}

fun KeyEvent.isBackBtn(): Boolean {
    return action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_BACK
}

fun KeyEvent.isPlayPause(): Boolean {
    return keyCode == KeyEvent.KEYCODE_MEDIA_PAUSE
            || keyCode == KeyEvent.KEYCODE_MEDIA_PLAY
            || keyCode == KeyEvent.KEYCODE_MEDIA_PLAY_PAUSE
}

fun KeyEvent.isHandledMediaKey(): Boolean {
    val keycodesToBeHandled = listOf(
        KeyEvent.KEYCODE_MEDIA_PLAY,
        KeyEvent.KEYCODE_MEDIA_PAUSE,
        KeyEvent.KEYCODE_MEDIA_PLAY_PAUSE,
        KeyEvent.KEYCODE_MEDIA_FAST_FORWARD,
        KeyEvent.KEYCODE_MEDIA_REWIND
    )

    return keyCode in keycodesToBeHandled
}
