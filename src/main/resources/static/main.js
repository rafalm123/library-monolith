async function submitLoginForm(event) {
    event.preventDefault();

    const username = document.getElementById("username").value;
    const password = document.getElementById("password").value;

    try {
        const response = await fetch("/auth/login", {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify({
                username: username,
                password: password,
            }),
        });

        if (response.ok) {
            const data = await response.json();
            const token = data.token;
            localStorage.setItem("jwt", token);
            window.location.href = "/home";
        } else {
            const errorMessage = document.createElement("p");
            errorMessage.textContent = "Niepoprawne dane logowania. Spróbuj ponownie.";
            errorMessage.style.color = "red";
            const form = document.querySelector(".form-signin");
            form.appendChild(errorMessage);
        }
    } catch (error) {
        console.error("Wystąpił błąd sieciowy:", error);
        const errorMessage = document.createElement("p");
        errorMessage.textContent = "Wystąpił błąd sieciowy. Spróbuj ponownie później.";
        errorMessage.style.color = "red";
        const form = document.querySelector(".form-signin");
        form.appendChild(errorMessage);
    }

    function logout() {
        localStorage.removeItem("jwt");
        window.location.href = "/auth/login";
    }
}
