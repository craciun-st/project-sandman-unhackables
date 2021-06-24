export function colorClassForCategory(someString) {
    if (!(typeof someString === "string")) {
      return "color-label-general";
    } else {
      return `color-label-${someString
        .toLowerCase()
        .split(" ")
        .join("-")
        .slice(0, Math.min(someString.length, 10))}`;
    }
  }