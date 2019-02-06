package com.example.vodfactorytest

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.bitmovin.player.BitmovinPlayer
import com.bitmovin.player.BitmovinPlayerView
import com.bitmovin.player.config.drm.DRMSystems
import com.bitmovin.player.config.media.SourceConfiguration
import com.bitmovin.player.config.media.SourceItem

class MainActivity : AppCompatActivity() {
    private val bitmovinPlayerView: BitmovinPlayerView get() = findViewById(R.id.bitmovinPlayerView)
    private var bitmovinPlayer: BitmovinPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bitmovinPlayer = bitmovinPlayerView.player

        val mediaPath = "https://cdn.vodfactory.com/dashnew/test_avengers_captainmarvel_vm_feature_V2/test_avengers_captainmarvel_vm_feature_V2.mpd"
        val drm = "https://wv.service.expressplay.com/hms/wv/rights/?ExpressPlayToken=BAAAAAgVKdMAAACAqNGlT-zTAai0QW1pI4Qk9MtT9TIHJU9xAa4u7kmWXIUgHf2_jrnrWJCPrwmLH8LLQQgL7A1-N_HFgX00Ry-CRscVLOZJpbVgVXRjkLOtJiFeC0GWiKO7tF8weCROkYOQJ3qGboJRe3aTGfP53xlxmc7arS2CRBCcGkLK4-HSmz1v48QOduPqm5ZLtc08xrIiWklr9Q"
        initializePlayer(mediaPath, drm)
    }

    private fun initializePlayer(mediaPath: String?, drm: String?) {
        // Create a new source configuration
        val sourceConfiguration = SourceConfiguration()

        // Create a new source item
        val sourceItem = SourceItem(mediaPath)

        // setup DRM handling
        //val drmLicenseUrl = "http://widevine-proxy.appspot.com/proxy"
        val drmSchemeUuid = DRMSystems.WIDEVINE_UUID
        sourceItem.addDRMConfiguration(drmSchemeUuid, drm)

        // Add source item including DRM configuration to source configuration
        sourceConfiguration.addSourceItem(sourceItem)

        // load source using the created source configuration
        bitmovinPlayer?.load(sourceConfiguration)
    }
}
