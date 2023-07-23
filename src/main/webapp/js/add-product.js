function changeParameter(value) {
	console.log("Il valore selezionato è:", value);
    var consoleSelect = document.getElementById("console");
    consoleSelect.innerHTML = ""; // Rimuovi tutte le opzioni precedenti

    switch (value) {
        case "nintendo":
            consoleSelect.innerHTML = `
                <option value="n64">n64</option>
                <option value="snes">snes</option>
                <option value="nes">nes</option>
                <option value="gamecube">gamecube</option>
                <option value="gameboy">gameboy</option>
            `;
            break;
        case "sony":
            consoleSelect.innerHTML = `
                <option value="ps1">ps1</option>
                <option value="ps2">ps2</option>
                <option value="psp">psp</option>
            `;
            break;
        case "sega":
            consoleSelect.innerHTML = `
                <option value="megadrive">megadrive</option>
                <option value="dreamcast">dreamcast</option>
            `;
            break;
        case "microsoft":
            consoleSelect.innerHTML = `
                <option value="xbox">xbox</option>
                <option value="xbox 360">xbox360</option>
            `;
            break;
        default:
            consoleSelect.innerHTML = "<option></option>";
            break;
    }
}
