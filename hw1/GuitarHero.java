public class GuitarHero {
    public static void main(String[] args) {
        /* create two guitar strings, for concert A and C */
        String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
        double freq = 20.81081;
        synthesizer.GuitarString[] str = new synthesizer.GuitarString[37];

        for (int i = 0; i < 37; i += 1) {
            str[i] = new synthesizer.GuitarString(Math.pow(2, (i - 24) / 12) * 440);
        }


        while (true) {

            /* check if the user has typed a key; if so, process it */
            if (StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();
                int index = keyboard.indexOf(key);
                if (index != -1) {
                    str[index].pluck();
                }
            }

            /* compute the superposition of samples */
            double sample = 0.0;
            for (int i = 0; i < 37; i++) {
                sample += str[i].sample();
            }

            /* play the sample on standard audio */
            StdAudio.play(sample);

            /* advance the simulation of each guitar string by one step */

            for (int i = 0; i < 37; i++) {
                str[i].tic();
            }
        }
    }
}
