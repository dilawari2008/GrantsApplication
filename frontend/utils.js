export const formatDateTime = (dateTimeString) => {
  const date = new Date(dateTimeString);

  const day = date.getUTCDate();
  const month = date.toLocaleString('default', { month: 'long', timeZone: 'UTC' });
  const year = date.getUTCFullYear();

  let hours = date.getUTCHours();
  const minutes = date.getUTCMinutes();
  const ampm = hours >= 12 ? 'PM' : 'AM';
  hours = hours % 12;
  hours = hours ? hours : 12; // the hour '0' should be '12'

  const daySuffix = (day) => {
      if (day > 3 && day < 21) return 'th'; // exceptions for 11th, 12th, 13th
      switch (day % 10) {
          case 1: return 'st';
          case 2: return 'nd';
          case 3: return 'rd';
          default: return 'th';
      }
  };

  const formattedMinutes = minutes < 10 ? '0' + minutes : minutes;

  return `${day}${daySuffix(day)} ${month}, ${year} ${hours}:${formattedMinutes} ${ampm} GMT`;
};

export const truncateText = (text) => {
  if (text.length > 20) {
    return text.slice(0, 20) + '...';
  }
  return text;
};
