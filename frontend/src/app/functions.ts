export const dateTimeFormatter = function(dateTime:string) {
    if (!dateTime) return '';
    const date = new Date(String(dateTime));
    return date.toLocaleString();
};