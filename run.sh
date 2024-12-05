#!/bin/bash

echo "Select File"
echo "0 - Menu"
echo "1 - BFS"
echo "2 - DFS"
read option
# option=1
if [[ "$option" == "0" ]]; then
  ant -Dnb.internal.action.name=run.single -Djavac.includes=JanelaPrincipal.java -Drun.class=JanelaPrincipal run-single
fi

if [[ "$option" == "1" ]]; then
  ant -Dnb.internal.action.name=run.single -Djavac.includes=SimuladorBuscaLargura.java -Drun.class=SimuladorBuscaLargura run-single
fi

if [[ "$option" == "2" ]]; then
  ant -Dnb.internal.action.name=run.single -Djavac.includes=SimuladorBuscaProfundidade.java -Drun.class=SimuladorBuscaProfundidade run-single 
fi
