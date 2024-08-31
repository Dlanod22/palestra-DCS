function toggleInsertModal()
{
    var modal = document.querySelector("#insert-modal");
    modal.hidden = !modal.hidden;
}

function openUpdateModal(id, nome, cognome, dataNascita, idPiano)
{
    document.querySelector("#update-modal").hidden = false;
    var modId = document.querySelector("#mod-id");
    var modNome = document.querySelector("#mod-nome");
    var modCognome = document.querySelector("#mod-cognome");
    var modDataNascita = document.querySelector("#mod-datanascita");
    var modPiano = document.querySelector("#mod-pianoAbbonamento");

    modId.value = id;
    modNome.value = nome;
    modCognome.value = cognome;
    modDataNascita.value = dataNascita;
    modPiano.value = idPiano;
}

function closeUpdateModal() 
{
    document.querySelector("#update-modal").hidden = true;
}


function openUpdateScheda(ese1, ese2, ese3, ese4, ese5, ese6, ese7, ese8, ese9)
{
    document.querySelector("#update-scheda").hidden = false;
    var modese1 = document.querySelector("#mod-ese1");
    var modese2 = document.querySelector("#mod-ese2");
    var modese3 = document.querySelector("#mod-ese3");
    var modese4 = document.querySelector("#mod-ese4");
    var modese5 = document.querySelector("#mod-ese5");
    var modese6 = document.querySelector("#mod-ese6");
    var modese7 = document.querySelector("#mod-ese7");
    var modese8 = document.querySelector("#mod-ese8");
    var modese9 = document.querySelector("#mod-ese9");

    modese1.value = ese1;;
    modese2.value = ese2;
    modese3.value = ese3;
    modese4.value = ese4;
    modese5.value = ese5;
    modese6.value = ese6;
    modese7.value = ese7;
    modese8.value = ese8;
    modese9.value = ese9;
}

function closeUpdateScheda() 
{
    document.querySelector("#update-scheda").hidden = true;
}







