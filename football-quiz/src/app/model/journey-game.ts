export interface JourneyGame {
  id: string;
  transfers: JourneyTransfer[];
}

export interface JourneyTransfer {
  clubFromId: number;
  clubFromName: string;
  clubFromImage: string;
  clubToId: number;
  clubToName: string;
  clubToImage: string;
  date: string;
  upcoming: boolean;
  season: string;
  fee: string;
}
