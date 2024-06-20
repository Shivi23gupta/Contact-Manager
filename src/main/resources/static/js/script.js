console.log("script loaded");

let currentTheme = getTheme();
//initial

document.addEventListener("DOMContentLoaded", () => {
  changeTheme();
});

//TODO
function changeTheme() {
  //set the listener to change theme button
  const changeThemeButton = document.querySelector("#theme_change_button");

  changeThemeButton.addEventListener("click", (event) => {
    let oldTheme = currentTheme;

    console.log("change theme button clicked");

    if (currentTheme === "dark") {
      //theme ko light
      currentTheme = "light";
    } else {
      //theme ko dark
      currentTheme = "dark";
    }

    console.log(currentTheme);
    changePageTheme(currentTheme, oldTheme);
  });
}

//set theme to localStorage
function setTheme(theme) {
  localStorage.setItem("theme", theme);
}

//get theme from localStorage
function getTheme() {
  let theme = localStorage.getItem("theme");
  return theme == null ? "light" : theme;
}

//change current page theme
function changePageTheme(newTheme, oldTheme) {
  //update in local storage
  setTheme(newTheme);
  //remove the current theme
  if (oldTheme) {
    document.querySelector("html").classList.remove(oldTheme);
  }
  //set current theme
  document.querySelector("html").classList.add(newTheme);

  //change the text of button
  document
    .querySelector("#theme_change_button")
    .querySelector("span").textContent = newTheme == "light" ? "Dark" : "Light";
}

//change page theme
