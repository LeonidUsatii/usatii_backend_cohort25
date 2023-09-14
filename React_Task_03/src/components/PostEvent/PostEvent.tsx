import { FormEvent, useEffect, useState } from 'react';
export default function PostEvent(): JSX.Element {
  const [title, setTitle] = useState<string>('');
  const [startDate, setStartDate] = useState<string>('');
  const [expirationDate, setExpirationDate] = useState<string>('');

  const postData = async () => {
    const url = 'http://localhost:8080/events';
    let data = {
      title: title,
      startDate: startDate,
      expirationDate: expirationDate,
    };
    const response = await fetch(url, {
      // Метод, если не указывать, будет использоваться GET
      method: 'POST',
      // Заголовок запроса
      headers: {
        'Content-Type': 'application/json',
      },
      // Данные
      body: JSON.stringify(data),
    });
    return response.json();
  };

  useEffect(() => {
      postData().then((data) => {});
  }, [expirationDate]);


  function handleSubmit(event: FormEvent<HTMLFormElement>): void {
    throw new Error('Function not implemented.');
  }

  return (
    <div>
      <h1>Форма создания дела</h1>
      <form onSubmit={handleSubmit}>
        <input
          type="text"
          placeholder="title"
          value={title}
          onChange={(e) => setTitle(e.target.value)}
        />
        <input
          type="date"
          placeholder="startDate"
          value={startDate}
          onChange={(e) => setStartDate(e.target.value)}
        />

        <input
          type="date"
          placeholder="startDate"
          value={expirationDate}
          onChange={(e) => setExpirationDate(e.target.value)}
        />
        <button type="submit">Создать</button>
       
      </form>

    </div>
  
  );

}
