package gh2;

import edu.princeton.cs.algs4.StdAudio;
import edu.princeton.cs.algs4.StdDraw;

public class GuitarHero {
    private static final double CONCERT_A = 440.0;
    private static final double CONCERT_C = CONCERT_A * Math.pow(2, 3.0 / 12.0);

    private static double getConcert(int i) {
        return CONCERT_A * Math.pow(2, (i-24) / 12.0);
    }

    public static void main(String[] args) {
        /* create two guitar strings, for concert A and C */
        String keyboard="q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";

        GuitarString stringNow = new GuitarString(getConcert(1));
        GuitarString stringPast = new GuitarString(getConcert(1));
        while (true) {
            /* check if the user has typed a key; if so, process it */


            if (StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();
                int index = keyboard.indexOf(key);
                stringNow = new GuitarString(getConcert(index));
                stringNow.pluck();
            }
            if (stringPast == null){
                stringPast = stringNow;
            }

            /* compute the superposition of samples */
            double sample = stringNow.sample() + stringPast.sample();

            /* play the sample on standard audio */
            StdAudio.play(sample);

            /* advance the simulation of each guitar string by one step */
            stringNow.tic();
            stringPast.tic();
        }
    }
}
