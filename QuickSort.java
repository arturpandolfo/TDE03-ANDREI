import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class QuickSort {
    private static long copiaContador = 0;
    private static long iteracaoContador = 0;

    public static void main(String[] args) {
        int[] tamanhos = {1000, 10000, 100000, 500000, 1000000};
        int rodadas = 5;

        System.out.printf("%-15s %-15s %-15s %-15s %n", "Tamanho", "Tempo Médio (ms)", "Cópias Médias", "Iterações Médias");


        try (FileWriter writer = new FileWriter("resultados.csv")) {
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
                    quickSort(vetor, 0, vetor.length - 1);
                    long fim = System.currentTimeMillis();

                    tempoTotal += (fim - inicio);
                    copiaTotal += copiaContador;
                    iteracaoTotal += iteracaoContador;
                }


                long tempoMedio = tempoTotal / rodadas;
                long copiasMedias = copiaTotal / rodadas;
                long iteracoesMedias = iteracaoTotal / rodadas;


                System.out.printf("%-15d %-15d %-15d %-15d %n", tamanho, tempoMedio, copiasMedias, iteracoesMedias);


                writer.write(tamanho + "," + tempoMedio + "," + copiasMedias + "," + iteracoesMedias + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void quickSort(int[] vetor, int inicio, int fim) {
        if (inicio < fim) {
            iteracaoContador++;
            int pivoIndex = partition(vetor, inicio, fim);
            quickSort(vetor, inicio, pivoIndex - 1);
            quickSort(vetor, pivoIndex + 1, fim);
        }
    }

    private static int partition(int[] vetor, int inicio, int fim) {
        int pivo = vetor[fim];
        int i = inicio - 1;

        for (int j = inicio; j < fim; j++) {
            iteracaoContador++;
            if (vetor[j] <= pivo) {
                i++;
                swap(vetor, i, j);
                copiaContador++;
            }
        }
        swap(vetor, i + 1, fim);
        copiaContador++;
        return i + 1;
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
