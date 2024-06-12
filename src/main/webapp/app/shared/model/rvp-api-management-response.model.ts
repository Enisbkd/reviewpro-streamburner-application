export interface IRvpApiManagementResponse {
  id?: number;
  source?: string | null;
  lodgingId?: number | null;
  fd?: Date | null;
  td?: Date | null;
  respondableCountsPositive?: number | null;
  respondableCountsNegative?: number | null;
  respondedCountsPositive?: number | null;
  respondedCountsNegative?: number | null;
}

export class RvpApiManagementResponse implements IRvpApiManagementResponse {
  constructor(
    public id?: number,
    public source?: string | null,
    public lodgingId?: number | null,
    public fd?: Date | null,
    public td?: Date | null,
    public respondableCountsPositive?: number | null,
    public respondableCountsNegative?: number | null,
    public respondedCountsPositive?: number | null,
    public respondedCountsNegative?: number | null,
  ) {}
}
