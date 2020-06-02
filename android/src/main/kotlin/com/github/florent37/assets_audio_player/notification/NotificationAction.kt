package com.github.florent37.assets_audio_player.notification

import java.io.Serializable

sealed class NotificationAction(val playerId: String) : Serializable {
    
    companion object {
        const val ACTION_STOP = "stop"
        const val ACTION_NEXT = "next"
        const val ACTION_PREV = "prev"
        const val ACTION_TOGGLE = "toggle"
        const val ACTION_SELECT = "select"
    }
    
    class Show(
            val isPlaying: Boolean,
            val audioMetas: AudioMetas,
            playerId: String,
<<<<<<< HEAD
            val notificationSettings: NotificationSettings
    ) : NotificationAction(playerId= playerId) {
        fun copyWith(isPlaying: Boolean? = null, audioMetas: AudioMetas? = null,
                     playerId: String? = null, notificationSettings: NotificationSettings? = null) : Show{
=======
            val notificationSettings: NotificationSettings,
            val durationMs: Long
    ) : NotificationAction(playerId= playerId) {
        fun copyWith(isPlaying: Boolean? = null,
                     audioMetas: AudioMetas? = null,
                     playerId: String? = null,
                     notificationSettings: NotificationSettings? = null,
                     durationMs: Long? = null
        ) : Show{
>>>>>>> aef903db08b6554d4dee86baea5b9f590778de5b
            return Show(
                    isPlaying= isPlaying ?: this.isPlaying,
                    audioMetas = audioMetas ?: this.audioMetas,
                    playerId = playerId ?: this.playerId,
<<<<<<< HEAD
                    notificationSettings = notificationSettings ?: this.notificationSettings
=======
                    notificationSettings = notificationSettings ?: this.notificationSettings,
                    durationMs = durationMs ?: this.durationMs
>>>>>>> aef903db08b6554d4dee86baea5b9f590778de5b
            )
        }
    }

    class Hide(playerId: String) : NotificationAction(playerId= playerId)
}
