public class Runner {

    public static void main(String[] args) {
        MainWindow mainWindow = new MainWindow();
        PlaySound playSound = new PlaySound();
        ReadWav redController = new ReadWav();
        redController.ReadWavFile("D:\\IwantGun\\WAVs\\м.wav");
    }
}
