console.log("script is loaded");

let currentTheme = getTheme();

document.addEventListener("DOMContentLoaded", () => {
  changeTheme();
});
console.log(currentTheme);

function changeTheme() {
  //set theme to the html
  //document.querySelector("html").classList.add(currentTheme);
  changePageTheme(currentTheme, "");
  //set the listner to change theme button
  const changeThemeButton = document.querySelector("#theme_change_button");

  changeThemeButton.addEventListener("click", (e) => {
    console.log("clicked button");
    let oldtheme = currentTheme;
    if (currentTheme === "dark") {
      currentTheme = "Light";
    } else {
      currentTheme = "dark";
    }
    changePageTheme(currentTheme, oldtheme);
  });
}
function setTheme(theme) {
  localStorage.setItem("theme", theme);
}
function getTheme() {
  let theme = localStorage.getItem("theme");
  return theme ? theme : "light";
}

function changePageTheme(theme, oldtheme) {
  //localstorge pr update krge then ui pr bhi represent krna hai
  setTheme(theme);
  //remove the current theme because of chnage
  if (oldtheme) {
    document.querySelector("html").classList.remove(oldtheme);
  }
  document.querySelector("html").classList.add(theme);

  document
    .querySelector("#theme_change_button")
    .querySelector("span").textContent = theme == "Light" ? "dark" : "Light";
}
