        function changeType(button) {
            var row = button.closest("tr");
            var inputs = row.querySelectorAll("input[type=text], input[type=password]");

            if (button.textContent.trim() === "Edit") {
                button.textContent = "Save";
                inputs.forEach(input => {
                    input.removeAttribute("readonly");
                });
            } else {
                button.textContent = "Edit";
                inputs.forEach(input => {
                    input.setAttribute("readonly", true);
                });
                // Submit form via AJAX or regular form submission
                document.getElementById("accountForm").submit();
            }
        }