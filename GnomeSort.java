import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class GnomeSort {
    private static long copiaContador = 0;
    private static long iteracaoContador = 0;

    public static void main(String[] args) {
        int[] tamanhos = {1000, 10000, 100000, 500000, 1000000};
        int rodadas = 5;


        System.out.printf("%-15s %-15s %-15s %-15s %n", "Tamanho", "Tempo Médio (ms)", "Cópias Médias", "Iterações Médias");


        try (FileWriter writer = new FileWriter("resultados_gnome_sort.csv")) {
            writer.write("Tamanho,Tempo Médio (ms),Cópias Médias,Iterações Médias\n");

            for (int tamanho : tamanhos) {
                long tempoTotal = 0;
                long copiaTotal = 0;
                long iteracaoTotal = 0;

                for (int i = 0; i < rodadas; i++) {
                    int[] vetor = gerarVetorAleatorio(tamanho, i);

                    copiaContador = 0;
                    iteracaoContador = 0;

                    long inicio = System.currentTimeMillis();
                    gnomeSort(vetor);
                    long fim = System.currentTimeMillis();

                    long tempoExecucao = fim - inicio;
                    tempoTotal += tempoExecucao;
                    copiaTotal += copiaContador;
                    iteracaoTotal += iteracaoContador;

                    System.out.printf("Rodada %d: Tempo = %d ms, Cópias = %d, Iterações = %d%n",
                            i + 1, tempoExecucao, copiaContador, iteracaoContador);
                }


                long tempoMedio = tempoTotal / rodadas;
                long copiasMedias = copiaTotal / rodadas;
                long iteracoesMedias = iteracaoTotal / rodadas;


                System.out.printf("%-15d %-15d %-15d %-15d %n", tamanho, tempoMedio, copiasMedias, iteracoesMedias);


                writer.write(tamanho + "," + tempoMedio + "," + copiasMedias + "," + iteracoesMedias + "\n");


                System.out.print("Tamanho " + tamanho + ": ");
                int escala = (int) (tempoMedio / 10);
                for (int j = 0; j < escala; j++) {
                    System.out.print("*");
                }
                System.out.println(" " + tempoMedio + " ms");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void gnomeSort(int[] vetor) {
        int index = 0;

        while (index < vetor.length) {
            iteracaoContador++;
            if (index == 0 || vetor[index] >= vetor[index - 1]) {
                index++;
            } else {
                swap(vetor, index, index - 1);
                copiaContador++;
            }
        }
    }

    private static void swap(int[] vetor, int i, int j) {
        int temp = vetor[i];
        vetor[i] = vetor[j];
        vetor[j] = temp;
        copiaContador++;
    }

    private static int[] gerarVetorAleatorio(int tamanho, int seed) {
        Random random = new Random(seed);
        int[] vetor = new int[tamanho];
        for (int i = 0; i < tamanho; i++) {
            vetor[i] = random.nextInt();
        }
        return vetor;
    }
}
