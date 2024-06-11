enum class Nivel { BASICO, INTERMEDIARIO, DIFICIL }      

data class Usuario(var nome: String, var email: String, val id: Int)

class GerenciaUsuario {
    private val usuarios = mutableListOf<Usuario>()

    //Funcao para adicionar usuario
    fun adicionarUsuario(usuario : Usuario) {
        usuarios.add(usuario)
        println("Usuario adicionado: $usuario")
    }

    //Funcao para remover usuario
    fun removeUsuario(id: Int) {
        val usuario = usuarios.find {it.id == id}

        if (usuario != null) {
            usuarios.remove(usuario)
            println("Usuario removido: $usuario")

        } else {
            println("Usuário com ID $id não encontrado")
        }
    }

    //Funcao para atualiazar usuario
    fun atualizaUsuario(id: Int, novoNome: String, novoEmail: String) {
        val usuario = usuarios.find {it.id == id}

        if (usuario != null) {
            usuario.nome = novoNome
            usuario.email = novoEmail
            println("usuario atualizado: $usuario")

        } else {
            println("Usuário com ID $id não encontrado.")
        }
    }

    //obter um usuario pelo id
    fun verUsuario(id: Int) : Usuario? {
        val usuario = usuarios.find {it.id == id}

        if (usuario != null) {
            println("Usuário encontrado: $usuario")
        } else {
            println("Usuário com ID $id não encontrado")
        }
        return usuario
    }

    // Função para imprimir todos os usuários
    fun listartUsers() {
        println("Lista de usuários: ")
        usuarios.forEach { println(it) }
    }

    // Função para obter todos os usuários (necessária para GerenciaFormacao)
    fun obterTodosUsuarios(): List<Usuario> {
        return usuarios
    }
}

data class ConteudoEducacional(val id: Int, var nome: String, var descricao: String, var duracao: Int)

class GerenciaConteudo {
    private val conteudos = mutableListOf<ConteudoEducacional>()

    //Descricao sobre a dificuldade do conteudo
    fun nivelConteudo() {
        val nivel = Nivel.BASICO
        val message = when (nivel) {
            Nivel.BASICO -> "Conteudo para iniciante"
            Nivel.INTERMEDIARIO -> "Conteudo nao e para iniciante"
            Nivel.DIFICIL -> "Conteudo complexo"
        }
        println(message)
    }

    // Função para adicionar um conteúdo educacional
    fun adicionaConteudo(conteudo: ConteudoEducacional) {
        conteudos.add(conteudo)
        println("Conteúdo educacional adicionado: $conteudo")
    }

    // Função para remover um conteúdo educacional pelo ID
    fun removeConteudo(id: Int) {
        val conteudo = conteudos.find { it.id == id }
        if (conteudo != null) {
            conteudos.remove(conteudo)
            println("Conteúdo educacional removido: $conteudo")
        } else {
            println("Conteúdo educacional com ID $id não encontrado.")
        }
    }

    // Função para atualizar um conteúdo educacional
    fun atualizaConteudo(id: Int, novoNome: String, novaDescricao: String, novaDuracao: Int) {
        val conteudo = conteudos.find { it.id == id }
        if (conteudo != null) {
            conteudo.nome = novoNome
            conteudo.descricao = novaDescricao
            conteudo.duracao = novaDuracao
            println("Conteúdo educacional atualizado: $conteudo")
        } else {
            println("Conteúdo educacional com ID $id não encontrado.")
        }
    }

    // Função para ler (obter) um conteúdo educacional pelo ID
    fun verConteudo(id: Int): ConteudoEducacional? {
        val conteudo = conteudos.find { it.id == id }
        if (conteudo != null) {
            println("Conteúdo educacional encontrado: $conteudo")
        } else {
            println("Conteúdo educacional com ID $id não encontrado.")
        }
        return conteudo
    }

    // Função para listar todos os conteúdos educacionais (opcional)
    fun listarConteudos() {
        println("Lista de conteúdos educacionais")
        conteudos.forEach { println(it) }
    }
}

data class Formacao(val id: Int, var nome: String, val instituicao: String = "DIO", val alunos: MutableList<Usuario> = mutableListOf())

class GerenciaFormacao(private val gerenciaUsuario: GerenciaUsuario) {
    private val formacoes = mutableListOf<Formacao>()

    // Função para adicionar uma formação
    fun adicionarFormacao(formacao: Formacao) {
        formacoes.add(formacao)
        println("Formação adicionada: $formacao")
    }

    // Função para remover uma formação pelo ID
    fun removerFormacao(id: Int) {
        val formacao = formacoes.find { it.id == id }

        if (formacao != null) {
            formacoes.remove(formacao)
            println("Formação removida: $formacao")
        } else {
            println("Formação com ID $id não encontrada.")
        }
    }

    // Função para atualizar uma formação
    fun atualizarFormacao(id: Int, novoNome: String) {
        val formacao = formacoes.find { it.id == id }

        if (formacao != null) {
            formacao.nome = novoNome
            println("Formação atualizada: $formacao")
        } else {
            println("Formação com ID $id não encontrada.")
        }
    }

    // Função para ler (obter) uma formação pelo ID
    fun verFormacao(id: Int): Formacao? {
        val formacao = formacoes.find { it.id == id }

        if (formacao != null) {
            println("Formação encontrada: $formacao")
        } else { 
            println("Formação com ID $id não encontrada.")
        }
        return formacao
    }

    // Função para listar todas as formações
    fun listarFormacoes() {
        println("Lista de formações:")
        formacoes.forEach { println(it) }
    }

    // Função para adicionar um aluno a uma formação
    fun adicionarAlunoFormacao(formacaoId: Int, alunoId: Int) {
        val formacao = formacoes.find { it.id == formacaoId }
        val aluno = gerenciaUsuario.verUsuario(alunoId)

        if (formacao != null && aluno != null) {
            formacao.alunos.add(aluno)
            println("Aluno ${aluno.nome} adicionado à formação ${formacao.nome}")
        } else {
            if (formacao == null) println("Formação com ID $formacaoId não encontrada.")
            if (aluno == null) println("Aluno com ID $alunoId não encontrado.")
        }
    }

    // Função para listar os alunos de uma formação
    fun listarAlunosFormacao(formacaoId: Int) {
        val formacao = formacoes.find { it.id == formacaoId }

        if (formacao != null) {
            println("Lista de alunos da formação ${formacao.nome}:")
            formacao.alunos.forEach { println(it) }
        } else {
            println("Formação com ID $formacaoId não encontrada.")
        }
    }
}

fun main() {
    val gerenciaUsuario = GerenciaUsuario()
    val gerenciaConteudo = GerenciaConteudo()
    val GerenciaFormacao = GerenciaFormacao(gerenciaUsuario)

    while (true) {
        println("""
            _____Escolha uma opção_____
            1. Gerenciar usuários
            2. Gerenciar conteúdos educacionais
            3. Gerenciar formações
            4. Sair
        """.trimIndent())

        when (val opcao = readLine()?.toIntOrNull()) {
            1 -> {
                println("""
                    _____Menu de gerenciamento de usuários_____
                    1. Adicionar usuário
                    2. Remover usuário
                    3. Atualizar usuário
                    4. Ver usuário
                    5. Listar usuários
                """.trimIndent())

                when (val usuarioOpcao = readLine()?.toIntOrNull()) {
                    1 -> {
                        print("Digite o nome do usuário: ")
                        val nome = readLine() ?: ""
                        print("Digite o email do usuário: ")
                        val email = readLine() ?: ""
                        print("Digite o ID do usuário: ")
                        val id = readLine()?.toIntOrNull() ?: 0
                        gerenciaUsuario.adicionarUsuario(Usuario(nome, email, id))
                    }
                    2 -> {
                        print("Digite o ID do usuário a ser removido: ")
                        val id = readLine()?.toIntOrNull() ?: 0
                        gerenciaUsuario.removeUsuario(id)
                    }
                    3 -> {
                        print("Digite o ID do usuário a ser atualizado: ")
                        val id = readLine()?.toIntOrNull() ?: 0
                        print("Digite o novo nome do usuário: ")
                        val novoNome = readLine() ?: ""
                        print("Digite o novo email do usuário: ")
                        val novoEmail = readLine() ?: ""
                        gerenciaUsuario.atualizaUsuario(id, novoNome, novoEmail)
                    }
                    4 -> {
                        print("Digite o ID do usuário: ")
                        val id = readLine()?.toIntOrNull() ?: 0
                        gerenciaUsuario.verUsuario(id)
                    }
                    5 -> gerenciaUsuario.obterTodosUsuarios()
                    else -> println("Opção inválida. Tente novamente.")
                }
            }
            2 -> {
                println("""
                    _____Menu de gerenciamento de conteúdos educacionais_____
                    1. Adicionar conteúdo educacional
                    2. Remover conteúdo educacional
                    3. Atualizar conteúdo educacional
                    4. Ver conteúdo educacional
                    5. Listar conteúdos educacionais
                """.trimIndent())

                when (val conteudoOpcao = readLine()?.toIntOrNull()) {
                    1 -> {
                        print("Digite o nome do conteúdo educacional: ")
                        val nome = readLine() ?: ""
                        print("Digite a descrição do conteúdo educacional: ")
                        val descricao = readLine() ?: ""
                        print("Digite a duração do conteúdo educacional: ")
                        val duracao = readLine()?.toIntOrNull() ?: 0
                        println("Digite o ID do conteúdo educacional: ")
                        val id = readLine()?.toIntOrNull() ?: 0
                        gerenciaConteudo.adicionaConteudo(ConteudoEducacional(id, nome, descricao, duracao))
                    }
                    2 -> {
                        print("Digite o ID do conteúdo educacional a ser removido: ")
                        val id = readLine()?.toIntOrNull() ?: 0
                        gerenciaConteudo.removeConteudo(id)
                    }
                    3 -> {
                        print("Digite o ID do conteúdo educacional a ser atualizado: ")
                        val id = readLine()?.toIntOrNull() ?: 0
                        print("Digite o novo nome do conteúdo educacional: ")
                        val novoNome = readLine() ?: ""
                        print("Digite a nova descrição do conteúdo educacional: ")
                        val novaDescricao = readLine() ?: ""
                        print("Digite a nova duração do conteúdo educacional: ")
                        val novaDuracao = readLine()?.toIntOrNull() ?: 0
                        gerenciaConteudo.atualizaConteudo(id, novoNome, novaDescricao, novaDuracao)
                    }
                    4 -> {
                        print("Digite o ID do conteúdo educacional: ")
                        val id = readLine()?.toIntOrNull() ?: 0
                        gerenciaConteudo.verConteudo(id)
                    }
                    5 -> gerenciaConteudo.listarConteudos()
                    else -> println("Opção inválida. Tente novamente.")
                }
            }
            3 -> {
                println("""
                    _____Menu de gerenciamento de formações_____
                    1. Adicionar formação
                    2. Remover formação
                    3. Atualizar formação
                    4. Ver formação
                    5. Listar formações
                    6. Adicionar aluno à formação
                    7. Listar alunos de uma formação
                """.trimIndent())

                when (val formacaoOpcao = readLine()?.toIntOrNull()) {
                    1 -> {
                        print("Digite o nome da formação: ")
                        val nome = readLine() ?: ""
                        print("Digite o ID da formação: ")
                        val id = readLine()?.toIntOrNull() ?: 0
                        print("Digite a instituição da formação (ou pressione Enter para usar o valor padrão 'DIO')")
                        val instituicao = readLine() ?: "DIO"
                        GerenciaFormacao.adicionarFormacao(Formacao(id, nome, instituicao))
                    }
                    2 -> {
                        print("Digite o ID da formação a ser removida: ")
                        val id = readLine()?.toIntOrNull()?: 0
                        GerenciaFormacao.removerFormacao(id)
                        }
                    3 -> {
                        print("Digite o ID da formação a ser atualizada: ")
                        val id = readLine()?.toIntOrNull() ?: 0
                        print("Digite o novo nome da formação: ")
                        val novoNome = readLine() ?: ""
                        GerenciaFormacao.atualizarFormacao(id, novoNome)
                        }
                    4 -> {
                        print("Digite o ID da formação: ")
                        val id = readLine()?.toIntOrNull() ?: 0
                        GerenciaFormacao.verFormacao(id)
                        }
                    5 -> GerenciaFormacao.listarFormacoes()
                    6 -> {
                        print("Digite o ID da formação: ")
                        val formacaoId = readLine()?.toIntOrNull() ?: 0
                        print("Digite o ID do aluno a ser adicionado à formação: ")
                        val alunoId = readLine()?.toIntOrNull() ?: 0
                        GerenciaFormacao.adicionarAlunoFormacao(formacaoId, alunoId)
                        }
                    7 -> {
                        print("Digite o ID da formação: ")
                        val formacaoId = readLine()?.toIntOrNull() ?: 0
                        GerenciaFormacao.listarAlunosFormacao(formacaoId)
                        }
                    else -> println("Opção inválida. Tente novamente.")
                    }
                }

            4 -> {
                println("Saindo...")
                return
            }

            else -> println("Opção inválida. Tente novamente.")
        }
    }
}