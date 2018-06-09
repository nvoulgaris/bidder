package com.bluebanana.bidder.usecase;

import java.util.Optional;

public interface UseCase<Request, Response> {
  Optional<Response> execute(Request request);
}
