package com.bluebanana.bidder.web;

import java.util.Optional;

public interface UseCase<Request extends RequestDto, Response extends ResponseDto> {

  Optional<Response> execute(Request request);
}
