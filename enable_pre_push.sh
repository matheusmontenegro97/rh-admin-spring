#!/bin/bash

HOOKS_DIR=".git/hooks"
HOOK_FILE="$HOOKS_DIR/pre-push"

echo "Configurando o hook de pré-push..."

if [ ! -d ".git" ]; then
  echo "Este diretório não é um repositório Git."
  exit 1
fi

if [ ! -d "$HOOKS_DIR" ]; then
  echo "Diretório de hooks não encontrado. Criando..."
  mkdir -p "$HOOKS_DIR"
fi

if [ -f "$HOOK_FILE" ]; then
  echo "Hook de pré-push já existe!"
  exit 0
fi

# Cria o hook de pré-push
cat <<EOL > "$HOOK_FILE"
#!/bin/bash

echo "Executando testes antes do push..."

# Rodar testes
if ./mvnw test; then
    echo "Todos os testes passaram. Push permitido."
    exit 0
else
    echo "Alguns testes falharam. Push abortado."
    exit 1
fi
EOL

chmod +x "$HOOK_FILE"

echo "Hook de pré-push configurado com sucesso!"
