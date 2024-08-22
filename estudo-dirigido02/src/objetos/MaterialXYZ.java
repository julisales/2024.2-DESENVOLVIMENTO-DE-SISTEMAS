package objetos;

public class MaterialXYZ {
    private String nome;
    private String importanciaEquipe;
    private String linhaDeFrente;
    private String existeSimilar;
    private String classificacao;

    public MaterialXYZ(String nome, String importanciaEquipe, String linhaDeFrente, String existeSimilar) {
        this.nome = nome;
        this.importanciaEquipe = importanciaEquipe;
        this.linhaDeFrente = linhaDeFrente;
        this.existeSimilar = existeSimilar;
        this.classificacao = determinarClassificacao();
    }

    public String getNome() {
        return nome;
    }

    public String getClassificacao() {
        return classificacao;
    }

    public String getDescricao() {
        switch (classificacao) {
            case "X":
                return "São os materiais sem importância com possibilidade de similar existente na empresa.";
            case "Y":
                return "São os materiais de importância mediana, com possibilidade de similar existente na empresa.";
            case "Z":
                return "São os materiais de importância vital, sem similar na empresa.";
            default:
                return "Descrição não disponível.";
        }
    }

    private String determinarClassificacao() {
        if (importanciaEquipe.equals("s") && linhaDeFrente.equals("s") && existeSimilar.equals("n")) {
            return "Z";
        } else if (importanciaEquipe.equals("n") && linhaDeFrente.equals("n") && existeSimilar.equals("s")) {
            return "X";
        } else {
            return "Y";
        }
    }
}
