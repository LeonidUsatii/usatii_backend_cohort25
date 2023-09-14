import { useEffect, useState } from "react";
import Event from "./types/Event";

export default function GetEvent(): JSX.Element {
  
  const [events, setEvents] = useState<Event[]>([]);

  async function loadEvents(): Promise<void> {
    const res = await fetch('http://localhost:8080/events');
    const arr = await res.json();
    setEvents(arr);
  }

  useEffect(() => {
    loadEvents();
  }, []);

  return (
    <div>
      <h1>Список дел:</h1>
      <ul>
        {events.map((product) => (
          <li key={product.id}>
            <span>{product.title + ' | '}</span>
            <span>{product.startDate + ' | '}</span>
            <span>{product.expirationDate + ' | '}</span>
          </li>
        ))}
      </ul>
    </div>
  );
}
