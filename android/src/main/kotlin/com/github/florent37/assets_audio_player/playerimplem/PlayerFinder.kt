package com.github.florent37.assets_audio_player.playerimplem

import android.content.Context
import com.github.florent37.assets_audio_player.AssetAudioPlayerThrowable
import io.flutter.embedding.engine.plugins.FlutterPlugin

interface PlayerImplemTester {
    @Throws(Exception::class)
    suspend fun open(configuration: PlayerFinderConfiguration , type: PlayerImplemTesterExoPlayer.Type): PlayerFinder.PlayerWithDuration
}

class PlayerFinderConfiguration(
        val assetAudioPath: String?,
        val flutterAssets: FlutterPlugin.FlutterAssets,
        val assetAudioPackage: String?,
        val audioType: String,
        val networkHeaders: Map<*, *>?,
        val context: Context,
        val onFinished: (() -> Unit)?,
        val onPlaying: ((Boolean) -> Unit)?,
        val onBuffering: ((Boolean) -> Unit)?,
        val onError: ((AssetAudioPlayerThrowable) -> Unit)?
)

object PlayerFinder {

    class PlayerWithDuration(val player: PlayerImplem, val duration: DurationMS)
    class NoPlayerFoundException(val why: AssetAudioPlayerThrowable? = null) : Throwable()

    private val ExoPlayerTester = PlayerImplemTesterExoPlayer

    private val playerImpls = listOf<PlayerImplemTesterExoPlayer.Type>(
            PlayerImplemTesterExoPlayer.Type.Default,
            PlayerImplemTesterExoPlayer.Type.DASH,
            PlayerImplemTesterExoPlayer.Type.HLS,
            PlayerImplemTesterExoPlayer.Type.SmoothStreaming
    )


    @Throws(NoPlayerFoundException::class)
    private suspend fun _findWorkingPlayer(
            remainingImpls: List<PlayerImplemTesterExoPlayer.Type>,
            configuration: PlayerFinderConfiguration
    ): PlayerWithDuration {
        if (remainingImpls.isEmpty()) {
            throw NoPlayerFoundException()
        }
        try {
            //try the first
            val playerWithDuration = ExoPlayerTester?.open(
                    configuration = configuration,
                    type = remainingImpls.first()
            )
            //if we're here : no exception, we can return it
            return playerWithDuration!!
        } catch (unrachable : AssetAudioPlayerThrowable.UnreachableException) {
            //not usefull to test all players if the first is UnreachableException
            throw NoPlayerFoundException(why= unrachable)
        } catch (t: Throwable) {
            //else, remove it from list and test the next
            val implsToTest = remainingImpls.toMutableList().apply {
                removeAt(0)
            }
            return _findWorkingPlayer(
                    remainingImpls = implsToTest,
                    configuration= configuration
            )
        }
    }

    @Throws(NoPlayerFoundException::class)
    suspend fun findWorkingPlayer(configuration: PlayerFinderConfiguration): PlayerWithDuration {
        return _findWorkingPlayer(
                remainingImpls= playerImpls,
                configuration= configuration
        )
    }
}