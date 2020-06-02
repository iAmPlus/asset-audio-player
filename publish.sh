cd assets_audio_player_web
./publish.sh
cd ..

flutter format lib/
pub publish --force

<<<<<<< HEAD
cd assets_audio_player_web
./publish.sh

=======
>>>>>>> aef903db08b6554d4dee86baea5b9f590778de5b
git commit -am "published" && git push