import 'package:flutter/foundation.dart';

import '../assets_audio_player.dart';

typedef NotificationAction = void Function(AssetsAudioPlayer player);

@immutable
class NotificationSettings {
  //region configs
  /// both android & ios
  final bool nextEnabled;

  /// both android & ios
  final bool playPauseEnabled;

  /// both android & ios
  final bool prevEnabled;

<<<<<<< HEAD
=======
  /// both android & ios
  final bool seekBarEnabled;

>>>>>>> aef903db08b6554d4dee86baea5b9f590778de5b
  /// android only
  final bool stopEnabled;
  //endregion

  //region customizers
  /// null for default behavior
  final NotificationAction customNextAction;

  /// null for default behavior
  final NotificationAction customPlayPauseAction;

  /// null for default behavior
  final NotificationAction customPrevAction;

<<<<<<< HEAD
=======
  /// null for default behavior
  final NotificationAction customStopAction;

>>>>>>> aef903db08b6554d4dee86baea5b9f590778de5b
  //no custom action for stop

  //endregion

  const NotificationSettings({
    this.playPauseEnabled = true,
    this.nextEnabled = true,
    this.prevEnabled = true,
    this.stopEnabled = true,
<<<<<<< HEAD
    this.customNextAction,
    this.customPlayPauseAction,
    this.customPrevAction,
=======
    this.seekBarEnabled = true,
    this.customNextAction,
    this.customPlayPauseAction,
    this.customPrevAction,
    this.customStopAction,
>>>>>>> aef903db08b6554d4dee86baea5b9f590778de5b
  });
}

void writeNotificationSettingsInto(
    Map<String, dynamic> params, NotificationSettings notificationSettings) {
  params["notif.settings.nextEnabled"] = notificationSettings.nextEnabled;
  params["notif.settings.stopEnabled"] = notificationSettings.stopEnabled;
  params["notif.settings.playPauseEnabled"] =
      notificationSettings.playPauseEnabled;
  params["notif.settings.prevEnabled"] = notificationSettings.prevEnabled;
<<<<<<< HEAD
=======
  params["notif.settings.seekBarEnabled"] = notificationSettings.seekBarEnabled;
>>>>>>> aef903db08b6554d4dee86baea5b9f590778de5b
}
