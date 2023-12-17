export interface SearchResult {
  pageNumber: number;
  lastPageNumber: number;
  players: PlayerResult[];
}

export interface PlayerResult {
  id: number;
  name: string;
  position: string;
  age: string;
}
